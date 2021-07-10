import { Injectable } from '@angular/core';
import { Router } from '@angular/router';


const TOKEN_KEY = 'auth-token';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  
  constructor(private router: Router) { }


  signOut(): void {
    window.sessionStorage.clear();
    window.location.reload();  // To disable AppComponent logout button
    this.router.navigate(['login']);
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
    setTimeout(() => {
      alert('Session expired! Please login again.');
      this.signOut();
     },1800000);   // auto logout and redirect to login page after 30 min
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

}
