import { Injectable } from '@angular/core';
import { 
  Router,
  CanActivate,
  ActivatedRouteSnapshot
} from '@angular/router';
import { AuthService } from './auth.service';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate{

constructor(public auth: AuthService, public router: Router) { }

canActivate(route:ActivatedRouteSnapshot):boolean{

     // this will be passed from the route config
    // on the data property
    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem('token');
    // decode the token to get its payload
    if(token){
      const tokenPayload = decode(token);
    if (
      !this.auth.isAuthenticated() || 
      tokenPayload["scope"] !== expectedRole
    ) {
      this.router.navigate(['login'],{ state: { redirect: this.router.url } });
      return false;
    }
    return true;
    }
    this.router.navigate(['login'],{ state: { redirect: this.router.url } });
    return false;
    

}

}
