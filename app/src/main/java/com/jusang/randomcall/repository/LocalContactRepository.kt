package com.jusang.randomcall.repository

import android.Manifest
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jusang.randomcall.R
import com.jusang.randomcall.model.ContactModel

class LocalContactRepository(var context: Context): ContactRepository() {
    override fun getContactList(): List<ContactModel> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return ArrayList()
        }

        var contacts: ArrayList<ContactModel> = arrayListOf()

        var resolver: ContentResolver = context.contentResolver;
        var uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        var projection: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER);

        var cursor: Cursor? = resolver.query(uri, projection, null, null, null)

        if (cursor != null) {
            while(cursor.moveToNext()) {
                var id: String = cursor.getString(0);
                var name: String = cursor.getString(1);
                var phone: String = cursor.getString(2);

                contacts.add(ContactModel(id, name, phone, R.drawable.profile_sample))
            }

            cursor.close()
        }

        return contacts
    }
}