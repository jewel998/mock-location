export interface MockLocationPlugin {
  isMockLocation(options: { whitelist: string[] }): Promise<{ isEnabled: boolean }>;
  isDevOptionsEnabled(): Promise<{ isEnabled: boolean }>;
}

export interface MockLocationResult {
  isEnabled: boolean;
}