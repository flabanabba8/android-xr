# Compose for XR Spatial UI

Source: https://developer.android.com/develop/xr/jetpack-xr-sdk/ui-compose
Fetched: 2026-05-21

## SpatialPanel
```kotlin
Subspace {
    SpatialPanel(
        SubspaceModifier.height(824.dp).width(1400.dp).transformingMovable(),
        resizePolicy = ResizePolicy(),
    ) {
        // 2D Compose content here
    }
}
```

## SubspaceModifier
.height(dp), .width(dp), .transformingMovable(), .movable(shouldScaleWithDistance), .resizable(), .offset(x, y, z)

## Orbiter
```kotlin
Orbiter(
    anchorPoint = OrbiterAnchorPoint.Bottom,
    offset = DpVolumeOffset(y = 96.dp),
) { /* controls content */ }
```

## Spatial Layouts
SpatialRow, SpatialColumn, SpatialBox, SpatialSpacer
Curve radius: 825dp recommended for multi-panel rows

## 3D Models
```kotlin
val modelState = rememberSpatialGltfModelState(source = SpatialGltfModelSource.fromPath(...))
SpatialGltfModel(state = modelState, modifier = SubspaceModifier)
```

## Spatial UI Components (2D/3D hybrid)
SpatialDialog — panel pushed back 125dp, fallback to Dialog
SpatialPopup — elevated popup, fallback to Popup
SpatialElevation — z-depth via SpatialElevationLevel
