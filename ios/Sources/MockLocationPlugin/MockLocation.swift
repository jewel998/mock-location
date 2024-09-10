import Foundation

@objc public class MockLocation: NSObject {
    @objc public func isMockLocation() -> Bool {
        return false
    }

    @objc public func isDevOptionsEnabled() -> Bool {
        return false
    }
}
