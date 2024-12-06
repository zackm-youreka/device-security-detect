export interface DeviceSecurityDetectPlugin {
  isJailBreakOrRooted(): Promise<{ result: boolean }>;
}
