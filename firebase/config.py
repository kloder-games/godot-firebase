def can_build(plat):
    return plat == 'android'

def configure(env):
    if env['platform'] == 'android':
        env.android_add_dependency("compile 'com.google.firebase:firebase-core:9.8.0'")
        env.android_add_dependency("compile 'com.google.firebase:firebase-messaging:9.8.0'")
		env.android_add_dependency("compile 'com.google.firebase:firebase-crash:9.8.0'")
        env.android_add_java_dir("android")
        env.android_add_default_config("applicationId '[your-game-package]'")
        env.disable_module()
