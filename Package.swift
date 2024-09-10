// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "Jewel998MockLocation",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "Jewel998MockLocation",
            targets: ["MockLocationPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "MockLocationPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/MockLocationPlugin"),
        .testTarget(
            name: "MockLocationPluginTests",
            dependencies: ["MockLocationPlugin"],
            path: "ios/Tests/MockLocationPluginTests")
    ]
)