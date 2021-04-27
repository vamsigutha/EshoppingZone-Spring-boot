import { Component, OnChanges, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { CategoryService } from './services/category.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit,OnChanges {

  collapseShow = "hidden";
  isCollapsed = true;
  showSubMenu = {
    "Appliances": "hidden",
    "Arts, Crafts & Sewing": "hidden",
    "Automotive Parts & Accessories": "hidden",
    "Baby": "hidden",
    "Beauty & Personal Care": "hidden",
    "Cell Phones & Accessories": "hidden",
    "Women": "hidden",
    "Men": "hidden",
    "Girls": "hidden",
    "Boys": "hidden",
    "Computers": "hidden",
    "Electronics": "hidden",
    "Garden & Outdoor": "hidden",
    "Grocery & Gourmet Food": "hidden",
    "Health & Household": "hidden",
    "Home & Kitchen": "hidden",
    "Luggage & Travel Gear": "hidden",
    "Musical Instruments": "hidden",
    "Pet Supplies": "hidden",
    "Video Games": "hidden"
}
  isSubMenuOpen = false;

  categories:Observable<any>;

  homeAndKitchen = {
    category:"garden",
    sub_category:[
      "Kids' Home Store",
      "Kitchen & Dining",
      "Bedding",
      "Furniture",
      "Home Décor",
      "Wall Art",
      "Lighting & Ceiling Fans",
      "Event & Party Supplies",
      "Heating, Cooling & Air Quality",
      "Irons & Steamers",
      "Vacuums & Floor Care",
      "Cleaning Supplies"
    ]
  }

  mensFashion = {
    category:"fashion-mens",
    sub_category:["Clothing", "Shoes", "Jewelry", "Watches"]
  }

  womensFashion = {
    category:"fashion-womens",
    sub_category:["Clothing", "Shoes", "Jewelry", "Watches", "Handbags"]
  }

  electronics = {
    category:"electronics",
    sub_category: [
      "Accessories & Supplies",
      "Camera & Photo",
      "Car & Vehicle Electronics",
      "Cell Phones & Accessories",
      "Computers & Accessories",
      "GPS, Finders & Accessories",
      "Headphones",
      "Home Audio"
    ]
  }

  appliances = {
    category:"appliances",
    sub_category:[
      "Refrigerators, Freezers & Ice Makers",
      "Laundry Appliances",
      "Ranges, Ovens & Cooktops",
      "Microwave Ovens",
      "Small Kitchen Appliances",
      "Heating, Cooling & Air Quality",
      "Vacuums & Floor Care",
      "Garbage Disposals & Compactors",
      "Parts & Accessories"
    ]
  }

  isAuthenticated:boolean=false;


  constructor(private categorySerice:CategoryService,private authService:AuthService,private router:Router,private route:ActivatedRoute) {

   }

  ngOnInit() {
    this.categories = this.categorySerice.getCategory();
    this.isAuthenticated = this.authService.isAuthenticated();
  }

  ngOnChanges(){
    this.isAuthenticated = this.authService.isAuthenticated();
  }



  toggleCollapseShow() {
    if(this.isCollapsed){
      this.collapseShow = 'bg-white';
    }else{
      this.collapseShow = 'hidden';
    }
    this.isCollapsed = !this.isCollapsed;
   
  }

  onClickMenu(id){

    if(this.showSubMenu[id]=="hidden"){
      this.showSubMenu[id]="px-5 m-3 flex flex-col space-y-2";
    }else{
      this.showSubMenu[id]="hidden";
    }  
    
    
  }



  onClickLoginButton(){

  }


  cart(){

  }

  search(e){
    this.router.navigate(["search",e.target.value]);
  }


}
