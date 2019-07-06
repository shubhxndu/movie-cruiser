import { getTestBed } from '@angular/core/testing'
import {BrowserDynamicTestingModule, platformBrowserDynamicTesting } from '@angular/platform-browser-dynamic/testing';

declare const __karma__ : any;
declare const require: any;

__karma__.loaded = function() {};

getTestBed().initTestEnvironment(
    BrowserDynamicTestingModule,
    platformBrowserDynamicTesting()
);

const context = require.context('./',true,/\.spec\.ts$/);
context.keys().map(context);

__karma__.start();