export interface MockLocationPlugin {
  isMockLocation(options: { whitelist: string[] }): Promise<{ isEnabled: boolean; apps?: string[] }>;
  isDevOptionsEnabled(): Promise<{ isEnabled: boolean }>;
}

export interface MockLocationResult {
  isEnabled: boolean;
}