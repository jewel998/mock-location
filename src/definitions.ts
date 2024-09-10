export interface MockLocationPlugin {
  isMockLocation(options: { whitelist: string[] }): Promise<{ isEnabled: boolean; packages?: string[] }>;
  isDevOptionsEnabled(): Promise<{ isEnabled: boolean }>;
}

export interface MockLocationResult {
  isEnabled: boolean;
}