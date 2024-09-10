import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(MockLocationPlugin)
public class MockLocationPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "MockLocationPlugin"
    public let jsName = "MockLocation"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "isMockLocation", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "isDevOptionsEnabled", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = MockLocation()

    @objc func isDevOptionsEnabled(_ call: CAPPluginCall) {
        call.resolve([
            "isEnabled": implementation.isDevOptionsEnabled()
        ])
    }

    @objc func isMockLocation(_ call: CAPPluginCall) {
        call.resolve([
            "isEnabled": implementation.isMockLocation()
        ])
    }
}
