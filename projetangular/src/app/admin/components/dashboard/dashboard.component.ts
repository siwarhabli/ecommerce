import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  products:any[]=[];
  constructor(private adminService :AdminService) { }
  ngOnInit(){
    this.getAllProducts();
    
  }

  
  
  getAllProducts(){
    this.products=[];
    this.adminService.getAllProducts().subscribe(res => {
      this.products = [];
      res.forEach(element => {
        element.processedImg = element.base64Image
          ? 'data:image/jpeg;base64,' + element.base64Image
          : 'assets/default-image.jpg';  // image par défaut si aucune image disponible
        this.products.push(element);
      });
    });
    

 

 
}}
