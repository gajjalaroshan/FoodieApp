// Protractor configuration file, see link for more information
// https://github.com/angular/protractor/blob/master/lib/config.ts

const { SpecReporter } = require('jasmine-spec-reporter');
const JasmineReporter = require('jasmine-reporters');

exports.config = {
  allScriptsTimeout: 11000,
  useAllAngular2AppRoots: true,
  specs: [
    'e2e/*.e2e-spec.ts'
  ],
  capabilities: {
    'browserName': 'chrome',
    chromeOptions: {
      args: ["--test-type","--no-sandbox"],
    }
  },
  directConnect: true,
  baseUrl: 'http://localhost:4200/',
  framework: 'jasmine',
  jasmineNodeOpts: {
    showColors: true,
    defaultTimeoutInterval: 30000,
    print: function() {}
  },
  onPrepare() {
    require('ts-node').register({
      project: 'e2e'
    });
    jasmine.getEnv().addReporter(new SpecReporter({ spec: { displayStacktrace: true } }));
    // jasmine.getEnv().addReporter(new JasmineReporter.JUnitXmlReporter({
    //   savePath: 'reports/protractor/cadettests/',
    //   consolidateAll: true,
    //   filePrefix: 'protractor-e2etest-result.xml'
    // }));
    // browser.driver.get(browser.baseUrl);
    console.log("this is from solution repo of Foodie Application..................!!!!!!!!!!");
  }
};
