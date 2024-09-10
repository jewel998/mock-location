# @jewel998/mock-location

Detect Mock Location in an Capacitor Application

## Install

```bash
npm install @jewel998/mock-location
npx cap sync
```

Add this to your app Manifest.xml

```xml
<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"/>
```
or,

```xml
<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"
    tools:ignore="QueryAllPackagesPermission" />
```

## API

<docgen-index>

* [`isMockLocation(...)`](#ismocklocation)
* [`isDevOptionsEnabled()`](#isdevoptionsenabled)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### isMockLocation(...)

```typescript
isMockLocation(options: { whitelist: string[]; }) => Promise<{ isEnabled: boolean; }>
```

| Param         | Type                                  |
| ------------- | ------------------------------------- |
| **`options`** | <code>{ whitelist: string[]; }</code> |

**Returns:** <code>Promise&lt;{ isEnabled: boolean; }&gt;</code>

--------------------


### isDevOptionsEnabled()

```typescript
isDevOptionsEnabled() => Promise<{ isEnabled: boolean; }>
```

**Returns:** <code>Promise&lt;{ isEnabled: boolean; }&gt;</code>

--------------------

</docgen-api>
