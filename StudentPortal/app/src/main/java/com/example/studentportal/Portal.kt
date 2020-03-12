package com.example.studentportal

data class Portal(val title: String,val link: String) {
    companion object {
        val links = arrayOf(
            "https://www.android-development.app/level-3-multi-screen-app/level3-task2",
            "https://www.android-development.app/level-3-multi-screen-app/level3-task1",
            "https://www.android-development.app/level-3-multi-screen-app/level3-example"
        )
        val title = arrayOf(
            "Task 3 Level 2",
            "Task 3 Level 1",
            "Task 3 Example"
        )
    }
}