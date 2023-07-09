package org.irham3.storyapp.utils

import org.irham3.storyapp.data.local.entity.StoryEntity
import org.irham3.storyapp.data.remote.response.StoryResponse

object DataMapper {
    fun mapStoryResponseToStoryEntity(input: List<StoryResponse>): List<StoryEntity> {
        val storyEntity = ArrayList<StoryEntity>()
        input.map {
            val story = StoryEntity(
                id = it.id,
                name = it.name,
                photoUrl = it.photoUrl,
                description = it.description
            )
            storyEntity.add(story)
        }
        return storyEntity
    }
}