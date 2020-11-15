package com.jusang.randomcall.repository

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.telephony.PhoneNumberUtils
import com.jusang.randomcall.R
import com.jusang.randomcall.model.ContactModel
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class LocalContactRepository(private var context: Context): ContactRepository() {

    override fun getContactList(): Single<List<ContactModel>> {
        return Single.just(getLocalContacts()).delay(2000, TimeUnit.MILLISECONDS);
    }

    private fun getLocalContacts(): List<ContactModel> {
        var contacts: ArrayList<ContactModel> = arrayListOf()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return contacts
        }

        var resolver: ContentResolver = context.contentResolver;
        var uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        var projection: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER);

        var cursor: Cursor? = resolver.query(uri, projection, null, null, null)

        if (cursor != null) {
            while(cursor.moveToNext()) {
                var id: String = cursor.getString(0);
                var name: String = cursor.getString(1);
                var phone: String = cursor.getString(2);
                var formattedPhone: String = PhoneNumberUtils.formatNumber(phone, Locale.getDefault().country)

                // TODO : photo uri
                contacts.add(ContactModel(id, name, formattedPhone, R.drawable.profile_sample))
            }

            cursor.close()
        }

        return contacts
    }
}