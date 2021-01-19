import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  navigateToHome() {
    return browser.get('/home');
  }

  navigateToLogin() {
    return browser.get('/login');
  }

  navigateToSignUp() {
    return browser.get('/signUp');
  }
  navigateToProfile() {
    return browser.get('/profile');
  }

  navigateToFavorites() {
    return browser.get('/favourites');
  }

  navigateToReset() {
    return browser.get('/reset');
  }
}
