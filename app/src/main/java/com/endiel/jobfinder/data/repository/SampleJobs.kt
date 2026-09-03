package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.model.Job

val sampleJobs = listOf(
    Job(
        id = "1",
        companyName = "Google",
        jobTitle = "Android developer",
        location = "Toronto, Canada",
        salary = "$90k - %200k",
        category = "Technology",
        "Full time",
        "Developer",
        "We are looking for an Android Developer to build high-quality mobile " +
                "applications and work with a talented engineering team.",
        listOf(
            "2+ years of Android development experience",
            "Strong Kotlin knowledge",
            "Experience with Jetpack Compose",
            "Understanding of REST APIs",
            "Knowledge of Git"
        ),
        listOf(
            "Build native apps using Kotlin or Java in Android Studio for high performance.",
            "Design user interfaces (UI) and user experiences (UX) that look great on many " +
                    "screen sizes.",
            "Test and fix bugs to make sure the app runs smoothly without crashing.",
            "Publish updates and manage app releases on the Google Play Console",
            "Create and manage APIs so the mobile app can talk to the server.",
            "Design databases to store user data, settings, and app content safely.",
            "Write server-side code using languages like Node.js, Python, or PHP.",
            "Connect cloud services for user logins, push notifications, and data storage.",
            "Collaborate with teams like product managers and designers to plan new features.",
            "Monitor app performance and server speed to fix slow loading times."
        )
    ),
    Job(
        id = "2",
        companyName = "Microsoft",
        jobTitle = "Software Engineer",
        location = "Vancouver, Canada",
        salary = "$100k - $140k",
        category = "Technology",
        "Full time",
        "Engineer",
        "We are looking for an Android Developer to build high-quality mobile " +
                "applications and work with a talented engineering team.",
        listOf(
            "2+ years of Android development experience",
            "Strong Kotlin knowledge",
            "Experience with Jetpack Compose",
            "Understanding of REST APIs",
            "Knowledge of Git"
        ),
        listOf(
            "Build native apps using Kotlin or Java in Android Studio for high performance.",
            "Design user interfaces (UI) and user experiences (UX) that look great on many " +
                    "screen sizes.",
            "Test and fix bugs to make sure the app runs smoothly without crashing.",
            "Publish updates and manage app releases on the Google Play Console",
            "Create and manage APIs so the mobile app can talk to the server.",
            "Design databases to store user data, settings, and app content safely.",
            "Write server-side code using languages like Node.js, Python, or PHP.",
            "Connect cloud services for user logins, push notifications, and data storage.",
            "Collaborate with teams like product managers and designers to plan new features.",
            "Monitor app performance and server speed to fix slow loading times."
        )
    ),

    Job(
        id = "3",
        companyName = "Adobe",
        jobTitle = "Product Designer",
        location = "Remote",
        salary = "$80k - $110k",
        category = "Design",
        "Remote",
        "Designer",
        "We are looking for an Android Developer to build high-quality mobile " +
                "applications and work with a talented engineering team.",
        listOf(
            "2+ years of Android development experience",
            "Strong Kotlin knowledge",
            "Experience with Jetpack Compose",
            "Understanding of REST APIs",
            "Knowledge of Git"
        ),
        listOf(
            "Build native apps using Kotlin or Java in Android Studio for high performance.",
            "Design user interfaces (UI) and user experiences (UX) that look great on many " +
                    "screen sizes.",
            "Test and fix bugs to make sure the app runs smoothly without crashing.",
            "Publish updates and manage app releases on the Google Play Console",
            "Create and manage APIs so the mobile app can talk to the server.",
            "Design databases to store user data, settings, and app content safely.",
            "Write server-side code using languages like Node.js, Python, or PHP.",
            "Connect cloud services for user logins, push notifications, and data storage.",
            "Collaborate with teams like product managers and designers to plan new features.",
            "Monitor app performance and server speed to fix slow loading times."
        )
    ),

    Job(
        id = "4",
        companyName = "Deloitte",
        jobTitle = "Financial Analyst",
        location = "Toronto, Canada",
        salary = "$70k - $95k",
        category = "Finance",
        "Full time",
        "Analyst",
        "We are looking for a financial analyst with incredible skills " +
                "in financial sector to work with our engineering team.",
        listOf(
            "Ability to process large volumes of complex data and spot actionable trends.",
            "Advanced proficiency in spreadsheet software, financial analysis tools, and " +
                    "enterprise resource planning (ERP) systems.",
            "Strong verbal and written communication to explain complex financial concepts " +
                    "to non-financial leaders.",
            "A bachelor’s degree in finance, accounting, economics, or a related field."
        ),
        listOf(
            "Build and maintain detailed models to forecast business performance or evaluate " +
                    "investment opportunities.",
            "Review past results, conduct variance analysis, and compare actual performance " +
                    "against forecasts or budgets.",
            "Prepare clear dashboards, reports, and presentations for executives, department " +
                    "heads, or clients.",
            "Research industry and economic trends to recommend ways to cut costs, price " +
                    "products, or improve profitability."
        )
    )
)