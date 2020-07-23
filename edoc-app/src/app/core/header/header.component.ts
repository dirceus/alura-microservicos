import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  usuario$;

  constructor(private userService: UserService, private router: Router) {
    this.usuario$ = userService.getUsuarioObservable();
  }

  ngOnInit(): void {
  }

  logout(): void{
    this.userService.logout();
    this.router.navigate(['']);
  }

}
