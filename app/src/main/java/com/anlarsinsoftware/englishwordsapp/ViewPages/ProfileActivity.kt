package com.anlarsinsoftware.englishwordsapp.ViewPages

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.anlarsinsoftware.englishwordsapp.Util.BaseCompact
import com.anlarsinsoftware.englishwordsapp.Entrance.SignInActivity
import com.anlarsinsoftware.englishwordsapp.Util.bagla
import com.anlarsinsoftware.englishwordsapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import android.view.Gravity
import android.view.MenuItem
import android.view.WindowManager
import android.widget.PopupMenu
import androidx.core.content.ContextCompat

class ProfileActivity : BaseCompact() {

    private lateinit var auth: FirebaseAuth
    private lateinit var profileImage: ImageView
    private lateinit var textName: TextView
    private lateinit var textEmail: TextView
    private lateinit var btnConnectReport: Button
    private val storageRef = FirebaseStorage.getInstance().reference
    private var selectedImageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = Firebase.auth
        profileImage = findViewById(R.id.profileImage)
        textName = findViewById(R.id.textName)
        textEmail = findViewById(R.id.textEmail)
        btnConnectReport = findViewById(R.id.btnConnectReport)

        val currentUser = auth.currentUser

        currentUser?.let {
            textName.text = it.displayName ?: "İsim girilmedi"
            textEmail.text = it.email ?: "E-posta yok"
            it.photoUrl?.let { uri ->
                Picasso.get()
                    .load(uri)
                    .placeholder(R.drawable.baseline_person_24)
                    .into(profileImage)
            }
        }

        btnConnectReport.setOnClickListener {
            bagla(RaporPage::class.java, false)
            Toast.makeText(this, "Raporlama sistemine bağlanılıyor...", Toast.LENGTH_SHORT).show()
        }
    }
    fun exitClick(view: View) {
        auth.signOut()
        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Uygulamadan Çıkılsın mı?")
            .setMessage("Çıkış yapmak istediğinize emin misiniz?")
            .setPositiveButton("Evet") { dialog, _ ->
                dialog.dismiss()
                auth.signOut()
                bagla(SignInActivity::class.java, true)
                finishAffinity()
            }
            .setNegativeButton("Hayır") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }




    fun setting(view: View) {
        val popup = PopupMenu(this, view, Gravity.END)

        popup.setForceShowIcon(true)
        val wrapper = ContextThemeWrapper(this, R.style.PopupMenuStyle)
        val popupMenu = PopupMenu(wrapper, view, Gravity.END)

        popupMenu.menuInflater.inflate(R.menu.profile_menu, popupMenu.menu)


        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(mPopup, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_edit_profile -> {
                    düzen(view)
                    true
                }
                R.id.menu_admin_login -> {
                    showAdminLoginDialog()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun showAdminLoginDialog() {
        val adminKodu = "projeyazilimyapimi"
        val input = EditText(this).apply {
            hint = "Admin kodunu girin"
            setTextColor(ContextCompat.getColor(this@ProfileActivity, R.color.black))
            setHintTextColor(ContextCompat.getColor(this@ProfileActivity, R.color.gray_600))
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle("Admin Girişi")
            .setMessage("Lütfen Admin Kodunu Girin:")
            .setView(input)
            .setPositiveButton("Giriş") { _, _ ->
                if (input.text.toString() == adminKodu) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Hoş geldin admin",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Hatalı kod!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .setNegativeButton("İptal") { dialog, _ ->
                dialog.dismiss()
            }
            .create()


        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)


        input.requestFocus()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        dialog.show()


        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(this, R.color.indigo_500))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(this, R.color.indigo_500))
    }

    fun düzen(view: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Profil Fotoğrafını Seç"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            profileImage.scaleX = 1.5f
            profileImage.scaleY = 1.5f

            Picasso.get()
                .load(selectedImageUri)
                .placeholder(R.drawable.baseline_person_24)
                .into(profileImage)
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Profil Fotoğrafı Güncellensin mi?")
                .setMessage("Bu fotoğrafı profil fotoğrafı olarak ayarlamak istiyor musunuz?")
                .setPositiveButton("Evet") { _, _ ->
                    uploadImageToFirebase(selectedImageUri!!)
                }
                .setNegativeButton("Hayır") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
            alertDialog.show()
        }
    }

    private fun uploadImageToFirebase(imageUri: Uri) {
        val fileRef = storageRef.child("profil_fotograflari/${auth.currentUser?.uid}.jpg")

        fileRef.putFile(imageUri)
            .addOnSuccessListener {
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    val profileUpdates = userProfileChangeRequest {
                        photoUri = uri
                    }
                    auth.currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Profil fotoğrafı güncellendi!", Toast.LENGTH_SHORT).show()
                            Picasso.get()
                                .load(uri)
                                .placeholder(R.drawable.baseline_person_24)
                                .into(profileImage)
                        }
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Yükleme başarısız oldu!", Toast.LENGTH_SHORT).show()
            }
    }
}
