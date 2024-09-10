import { WebPlugin } from '@capacitor/core';

import type { MockLocationPlugin, MockLocationResult } from './definitions';

export class MockLocationWeb extends WebPlugin implements MockLocationPlugin {
  async isMockLocation(): Promise<MockLocationResult> {
    return { isEnabled: false };
  }

  async isDevOptionsEnabled(): Promise<MockLocationResult> {
    return { isEnabled: false };
  }
}
