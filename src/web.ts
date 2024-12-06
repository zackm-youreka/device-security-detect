import { WebPlugin } from '@capacitor/core';

import type { DeviceSecurityDetectPlugin } from './definitions';

export class DeviceSecurityDetectWeb extends WebPlugin implements DeviceSecurityDetectPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
