import { WebPlugin } from '@capacitor/core';

import type { MockLocationPlugin, MockLocationResult } from './definitions';

export class MockLocationWeb extends WebPlugin implements MockLocationPlugin {
  async isMockLocation(options: { whitelist: string[]; }): Promise<MockLocationResult> {
    throw new Error('Method not implemented.'+options);
  }

  async isDevOptionsEnabled(): Promise<MockLocationResult> {
    throw new Error('Method not implemented.');
  }
}
