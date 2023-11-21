package com.riti.data.enums

import com.fasterxml.jackson.annotation.JsonCreator

/**
 * 취미
 */
enum class InterestsEnum(val code: Int, val description: String) {
    Pet(0, "Pet"),
    Movie(1, "Movie"),
    Sns(2, "Sns"),
    Beauty(3, "Beauty"),
    Soccer(4, "Soccer"),
    Baseball(5, "Baseball"),
    Tennis(6, "Tennis"),
    Yoga(7, "Yoga"),
    Travel(8, "Travel"),
    CommunityInvolvement(9, "CommunityInvolvement"),
    Writing(10, "Writing"),
    Blogging(11, "Blogging"),
    LearningLanguages(12, "LearningLanguages"),
    Photography(13, "Photography"),
    Acting(14, "Acting"),
    Sports(15, "Sports"),
    Reading(16, "Reading"),
    MakingMusic(17, "MakingMusic"),
    Art(18, "Art"),
    Dance(19, "Dance"),
    Animation(20, "Animation"),
    AnimalRescue(21, "AnimalRescue"),
    Poetry(22, "Poetry"),
    Singing (23, "Singing "),
    Songwriting(24, "Songwriting"),
    Bowling(25, "Bowling"),
    Hiking(26, "Hiking"),
    MartialArts(27, "Martial arts"),
    Snowboarding(28, "Snowboarding"),
    Running(29, "Running"),
    CarRacing(30, "Car racing"),
    CookingClasses(31, "Cooking classes"),
    NONE(-1, "NONE");
    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(code: Int?): InterestsEnum =
            InterestsEnum.values().firstOrNull { it.code == code } ?: NONE
    }
}