# First Activity for AI Glasses

Source: https://developer.android.com/develop/xr/jetpack-xr-sdk/ai-glasses/first-activity
Fetched: 2026-05-21

## Key Concept: Projected Activity
Runs on phone, projected to glasses. Shares business logic between phone and glasses.

## Manifest
```xml
<activity
    android:name="com.example.xr.projected.GlassesMainActivity"
    android:exported="true"
    android:requiredDisplayCategory="xr_projected"
    android:label="Example activity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
    </intent-filter>
</activity>
```

Key: android:requiredDisplayCategory="xr_projected"

## Activity Setup
- Extend ComponentActivity
- Check ProjectedDeviceController.capabilities for CAPABILITY_VISUAL_UI
- Use ProjectedDisplayController for display management
- Use ProjectedPermissionsResultContract for hardware permissions
- Use GlimmerTheme for UI

## Start Activity
```kotlin
val options = ProjectedContext.createProjectedActivityOptions(context)
val intent = Intent(context, GlassesMainActivity::class.java)
context.startActivity(intent, options.toBundle())
```

## Check Connection
```kotlin
ProjectedContext.isProjectedDeviceConnected(context, coroutineContext).collect { isConnected -> }
```

## Check Capabilities at runtime — not all glasses have displays
