const { SpecReporter } = require('jasmine-spec-reporter');


exports.config = {
    allScriptsTimeout = 11000,
    specs: [
        './e2e/**/*.e2e-spec.ts'
    ],
    capabilites: {
        'browserName':'chrome'
    },
    directConnect: true,
    baseUrl: 'http://localhost:4200',
    framework: 'jasmine',
    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 30000,
        print: function(){}
    },
    onPrepare(){
        require('ts-node').register({
            project:'e2e/tsconfig.e2e.json'
        });
        broswer.driver.manage().window.setSize(1600,1000);
        jasmine.getEnv().addReporter(new SpecReporter({spec: {displayStacktrace: true}}));
    }

}
