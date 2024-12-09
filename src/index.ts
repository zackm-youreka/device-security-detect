import { registerPlugin } from '@capacitor/core';

import type { DeviceSecurityDetectPlugin } from './definitions';

const DeviceSecurityDetect = registerPlugin<DeviceSecurityDetectPlugin>('DeviceSecurityDetect', {
  web: () => import('./web').then((m) => new m.DeviceSecurityDetectWeb()),
});

export * from './definitions';
export { DeviceSecurityDetect };
