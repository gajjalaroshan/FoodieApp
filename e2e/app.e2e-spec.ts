import { AppPage } from './app.po';
import {browser, by, element} from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
    browser.sleep(1000);
    
  });

  it('should display message saying app works', () => {
    page.navigateTo();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('FoodieApp');
  });

  it('should go to home page and display restaurants by city and cuisine', () =>{
    page.navigateToHome;
    browser.sleep(1000);
    browser.element(by.id('city')).sendKeys('chennai');
    browser.element(by.id('cuisine')).sendKeys('italian');
    browser.element(by.id('search')).click();
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/home');
    browser.refresh();
  });

  it('should go to login application and signup', () =>{
    page.navigateToHome;
    browser.element(by.id('login')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
    browser.sleep(1000);
    browser.refresh();
  });

  it('should go to signup', () =>{
    browser.sleep(1000);
    browser.element(by.id('register')).click();
    expect(browser.getCurrentUrl()).toContain('/signUp');
    browser.sleep(1000);
  });

  it('should signup', () =>{
    browser.sleep(1000);
    browser.element(by.id('newusername')).sendKeys('RoshanJack');
    browser.element(by.id('email')).sendKeys('sandhya24532@gmail.com');
    browser.element(by.id('newpassword')).sendKeys('Sandhya@123');
    browser.element(by.id('confirmPassword')).sendKeys('Sandhya@123');
    browser.element(by.id('signup')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
    browser.sleep(1000);
  });

  it('should go to login application and login', () =>{
    page.navigateToLogin;
    browser.sleep(1000);
    browser.element(by.id('email')).sendKeys('sandhya24532@gmail.com');
    browser.element(by.id('password')).sendKeys('Sandhya@123');
    browser.element(by.id('logins')).click();
    expect(browser.getCurrentUrl()).toContain('/home');
    browser.sleep(1000);
    browser.refresh();
  });

  // it('should go to home page, add restaurants to favorites', () =>{
  //   page.navigateToHome;
  //   browser.sleep(1000);
  //   browser.element(by.id('city')).sendKeys('chennai');
  //   browser.element(by.id('cuisine')).sendKeys('italian');
  //   browser.element(by.id('search')).click();
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   expect(browser.getCurrentUrl()).toContain('/home');
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  //   browser.sleep(1000);
  // });

  // it('should go to favorites page and delete favorites, then go to home', () =>{
  //   page.navigateToFavorites;
  //   browser.sleep(1000);
  //   // browser.element(by.id('userId')).sendKeys('sandhya2@gmail.com');
  //   // browser.element(by.id('password')).sendKeys('Sandhya@123');
  //   browser.element(by.id('adddelete')).click();
  //   browser.sleep(1000);
  //   browser.refresh();
  // });


  it('should go to profile', () =>{
    page.navigateToHome;
    browser.sleep(1000);
    browser.element(by.id('Dropdown')).click();
    browser.element(by.id('profile')).click();
    browser.sleep(1000);
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/profile');
    browser.sleep(1000);
  });

  it('should update personal details', () =>{
    browser.sleep(1000);
    browser.element(by.id('edit')).click();
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    browser.element(by.id('edit')).click();
    // browser.element(by.id('firstname')).sendKeys('Santhosh');
    browser.element(by.id('lastname')).sendKeys('Sandhya');
    browser.element(by.id('mobile')).sendKeys('7369589745');
    browser.element(by.id('state')).sendKeys('Telangana');
    browser.element(by.id('city')).sendKeys('Hyderabad'); 
    browser.element(by.id('save')).click();
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/profile');
    browser.sleep(1000);
  });

  it('should reset password', () =>{
    browser.sleep(1000);
    browser.element(by.id('collapse')).click();
    browser.sleep(1000);
    browser.sleep(1000);
    browser.element(by.id('newPassword')).sendKeys('Santhu@123');
    browser.element(by.id('confirmPassword')).sendKeys('Santhu@123');
    browser.element(by.id('changePwdBtn')).click();
    browser.sleep(1000);
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/profile');
    browser.sleep(1000);
  });

  
  it('should logout', () =>{
    browser.sleep(1000);
    browser.element(by.id('Dropdown')).click();
    browser.element(by.id('logout')).click();
    browser.sleep(1000);
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/login');
    browser.sleep(1000);
  });
});
