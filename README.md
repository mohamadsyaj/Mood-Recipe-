# Mood-Based Recipe Selector

An Android application in **Kotlin** that suggests recipes tailored to your current mood.  
The user first chooses a mood (happy, sad, angry, or bored) in the **MoodSelectorFragment**.  
A corresponding keyword is mapped to that mood (for example, “happy” → “fresh”), and a request is sent to the **Spoonacular** API to fetch matching recipes, which are then displayed in a scrollable list. Architecture follows MVVM with a shared `RecipeRepository`.

---

## Project Structure

app/
└─ src/main/
├─ java/com/example/project_4/
│ ├─ api/
│ │ └─ RetrofitBuilder.kt ← Retrofit client + API interface
│ ├─ model/
│ │ └─ Recipe.kt ← data class (id, title, image, etc.)
│ ├─ repository/
│ │ └─ RecipeRepository.kt ← remote data source logic
│ ├─ viewmodel/
│ │ ├─ RecipeViewModel.kt
│ │ └─ RecipeViewModelFactory.kt
│ ├─ MoodSelectorFragment.kt ← mood buttons, navigation
│ ├─ RecipeListFragment.kt ← observes ViewModel, shows list
│ └─ RecipeAdapter.kt ← RecyclerView adapter
├─ res/
│ ├─ layout/
│ │ ├─ fragment_mood_selector.xml
│ │ ├─ fragment_recipe_list.xml
│ │ └─ item_recipe.xml
│ └─ drawable/
│ └─ placeholder.png ← fallback image
└─ AndroidManifest.xml

pgsql
Copy

### Key Classes & Layout

File | Role
--- | ---
`RecipeViewModel.kt` | Exposes recipe `LiveData` and handles coroutine calls to the repository.
`MoodSelectorFragment.kt` | Maps button clicks to moods and navigates to the list fragment with a bundle argument.
`RecipeListFragment.kt` | Displays recipes, shows progress indicator, handles empty results.
`RecipeAdapter.kt` | Binds recipe data, toggles instruction expansion on tap.
`RetrofitBuilder.kt` | Builds a Retrofit instance for the Spoonacular REST API (not shown here).

---

## Getting Started

1. Open the project in Android Studio Giraffe (or newer).  
2. Confirm these libraries in your `build.gradle`:

   ```gradle
   implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0"
   implementation "com.squareup.retrofit2:retrofit:2.9.0"
   implementation "com.squareup.retrofit2:converter-gson:2.9.0"
   implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1"
   implementation "com.github.bumptech.glide:glide:4.16.0"
   kapt "com.github.bumptech.glide:compiler:4.16.0"
Build and run on a device or emulator (API 21+).

API Key Setup
Spoonacular requires an API key.

Register at https://spoonacular.com/food-api

Copy your key and insert it in RetrofitBuilder.kt (or wherever headers are set).
Do not hard-code a personal key in a public repo; instead use local.properties or environment variables in production.

Customization Ideas
Idea	Where to Change
Add more moods/keywords	Extend the when block in RecipeViewModel.fetchRecipesForMood.
Change result count or filters	Modify the endpoint parameters in RecipeRepository.getRecipesForMood.
Offline caching	Add Room or the Paging library; cache API responses locally.
Open full recipe in web view	Add click listener in RecipeAdapter to launch an Intent.ACTION_VIEW with recipe.sourceUrl.

Roadmap
Unit tests for repository and ViewModel

DataStore or Room cache for offline usage

Jetpack Compose migration

Dark theme styling and adaptive color

Continuous Integration workflow

License
© 2025 Mohamad Syaj.Ahmad Hashem, Jad jonaidi All Rights Reserved.
This project is provided for personal or educational study only.
You may not sell, redistribute, or use the code in commercial products without explicit written consent from the author.

Author
Mohamad Syaj
For questions or suggestions, please open an issue or submit a pull request.
