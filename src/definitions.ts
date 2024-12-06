export interface DeviceSecurityDetectPlugin {
  isJailBreakOrRooted(): Promise<{ value: boolean }>;
}
