export interface DeviceSecurityDetectPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
