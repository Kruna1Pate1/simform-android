package com.krunal.demo.recyclerview.models

data class ChatDetail(
    val contactDetail: ContactDetail, val chats: List<ContactDetail> = emptyList()
) {

    companion object {

        val dummyDetails: List<ChatDetail> = buildList {
            repeat(20) {
                add(
                    ChatDetail(ContactDetail.dummyData.random(), List(3 * it) {
                        ContactDetail.dummyData.random()
                    })
                )
                add(
                    ChatDetail(ContactDetail.dummyData.random(), List(2 * it) {
                        ContactDetail.dummyData.random()
                    })
                )
            }
        }
    }
}