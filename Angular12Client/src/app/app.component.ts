import { Component, AfterViewChecked, OnInit, HostListener } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';
import { Router } from '@angular/router';


export let browserRefresh = false;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewChecked, OnInit {
  isLoggedIn = false;

  @HostListener("window:beforeunload", ["$event"]) unloadHandler(event: Event) {  // on browser refresh, logout user
    console.log("Processing beforeunload...");
    this.isLoggedIn = false;
    this.tokenStorageService.signOut();
  }

  constructor(private tokenStorageService: TokenStorageService, private router: Router) { }

  ngOnInit() {
    console.log('\n\nApp Component ngOnInit called...\n\n');
    
  }

  ngAfterViewChecked() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
  }

  logout(): void {
    this.isLoggedIn = false;
    this.tokenStorageService.signOut();
  }
}
