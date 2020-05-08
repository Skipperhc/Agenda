package repository

import entity.ContactEntity

class ContactRepository {

    companion object {
        private val contactList = mutableListOf<ContactEntity>()
        fun save(contact: ContactEntity) {
            contactList.add(contact)
        }

        fun delete(contact: ContactEntity) {
//          contactList.remove(contact)
            for(item in contactList.withIndex()) {
                if(item.value.name == contact.name && item.value.phone == contact.phone) {
                    contactList.removeAt(item.index)
                    break
                }
            }
        }

        fun getList() : List<ContactEntity>{
            return contactList
        }
    }
}