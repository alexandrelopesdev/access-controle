package cv.tryon.accesscontrol.domain.adapters

import cv.tryon.accesscontrol.data.model.Entry
import cv.tryon.accesscontrol.domain.model.EntryBO

class EntryAdapter {

    companion object {

        fun toModel(entry: Entry) = EntryBO(
            id = entry.id,
            userTag = entry.userTag,
            createdAt = entry.createdAt,
            updatedAt = entry.updatedAt,
        )

        fun toModel(entry: EntryBO) = Entry(
            userTag = entry.userTag,
            createdAt = entry.createdAt,
            updatedAt = entry.updatedAt,
        )
    }
}


