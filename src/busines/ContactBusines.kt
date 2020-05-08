package busines

import entity.ContactEntity
import repository.ContactRepository
import java.lang.Exception
import java.lang.RuntimeException

class ContactBusines {

    private fun validade(name:String, phone:String) {
        if(name == "") throw Exception("Nome é obrigatório")
        if(phone == "") throw Exception("Telefone é obrigatório")
    }

    fun getContactCount() : String{
        val list = getList()
        return when {
            list.isEmpty() -> "0 Contatos"
            list.size == 1 -> "1 Contato"
            else -> "${list.size} Contatos"
        }
    }

    private fun validadeDelete(name:String, phone:String) {
        if(name == "" || phone == "") throw VitorException("É necessário selecionar um contato antes de remover")
    }

    fun save(name:String, phone:String) {
        validade(name,phone)
        val contact = ContactEntity(name, phone)
        ContactRepository.save(contact)
    }

    @Throws(RuntimeException::class)
    fun delete(name:String, phone:String) {
        validadeDelete(name, phone)

        val contact = ContactEntity(name, phone)
        ContactRepository.delete(contact)
    }

    fun getList() : List<ContactEntity>{
        return ContactRepository.getList()
    }
}

class VitorException(msg:String) : Exception(msg)