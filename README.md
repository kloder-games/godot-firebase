# GodotFirebase

This is the Firebase module for Godot Engine (https://github.com/okamstudio/godot)
- Android only
- Firebase Core
- Firebase Messaging

## How to use

1. Drop the "*firebase*" directory inside the "*modules*" directory on the Godot source.
2. Add to the "*platform/android/java/build.gradle.template*" from the Godot source

	```
	buildscript {
	    // ...
	    dependencies {
	        // ...
	        classpath 'com.google.gms:google-services:3.0.0'
	    }
	}
	```

3. Add to the end of "*platform/android/java/build.gradle.template*" from the Godot source

	```
	// ADD THIS AT THE BOTTOM
	apply plugin: 'com.google.gms.google-services'
	```

4. Change "[your-game-package]" in the file "*modules/firebase/config.py*" by your package name (for example: com.brand.my-game)


5. Add your "*google-services.json*" configuration file to the path "*platform/android/java/*", this file is what you get on register your App on Firebase.

6. Recompile.

7. In your project goto *Export > Target > Android*:

	```
	Options:
		Custom Package:
			- place your apk from build
		Permissions on:
			- Access Network State
			- Internet
	```

8. Enable the module on Android, add the path to the module to the **modules** property on the **[android]** section of your **engine.cfg** file. It should look like this:

	```
	[android]
	modules="org/godotengine/godot/GodotGoogleAnalytics"
	```

	If you have more separete by comma.

## API Reference


The following methods are available:
```python

# Initialize the module
init()

# Upload score
# @param int score The score to upload
# @param int level The level to upload
# @param string character The character
postScore(score, level, character)

# Log die
# @param string level The level to upload when die
logDie(level)

# On Share
# @param string content_type The content type
# @param string item_id The item ID
share(content_type, item_id)

# On level up
# @param int level The level
# @param string character The character
levelUp(level, character)

# On achievement unlocked
# @param string achievement_id The achievement ID
unlockAchievement(achievement_id)

# On tutorial begin
tutorialBegin()

# On tutorial complete
tutorialComplete()

# On spent virtual currency
# @param string item_name The item bought
# @param string virtual_currency_name The name of the virtual currency
# @param int value The value of the item
spend_virtual_currency(item_name, virtual_currency_name, value)

# Log any event
# @param string event The event to log
logEvent(event)
```

## References

Google Firebase:
* https://firebase.google.com/docs/android/setup

## License

MIT license
