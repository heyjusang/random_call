package com.jusang.randomcall

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.jusang.randomcall.model.ContactModel
import com.jusang.randomcall.repository.ContactRepository
import com.jusang.randomcall.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(TestContactRepository())
    }

    @Test
    fun getContactList() {
        var contactList: LiveData<List<ContactModel>> = viewModel.getContactList()
        var i: Int = 1
        for (c in contactList.value!!) {
            Assert.assertEquals("id$i", c.id)
            Assert.assertEquals("name$i", c.name)
            Assert.assertEquals("010-1234-432$i", c.phone)
            i++
        }
    }

    class TestContactRepository: ContactRepository() {
        override fun getContactList(): List<ContactModel> {
            var contacts: ArrayList<ContactModel> = arrayListOf()

            contacts.add(ContactModel("id1", "name1", "010-1234-4321", R.drawable.profile_sample))
            contacts.add(ContactModel("id2", "name2", "010-1234-4322", R.drawable.profile_sample))
            contacts.add(ContactModel("id3", "name3", "010-1234-4323", R.drawable.profile_sample))
            contacts.add(ContactModel("id4", "name4", "010-1234-4324", R.drawable.profile_sample))
            contacts.add(ContactModel("id5", "name5", "010-1234-4325", R.drawable.profile_sample))
            contacts.add(ContactModel("id6", "name6", "010-1234-4326", R.drawable.profile_sample))
            contacts.add(ContactModel("id7", "name7", "010-1234-4327", R.drawable.profile_sample))

            return contacts
        }
    }
}