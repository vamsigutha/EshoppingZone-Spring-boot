import { Component, OnInit } from '@angular/core';
import { DeliveryAgentService } from './services/delivery-agent.service';

@Component({
  selector: 'app-delivery-agent',
  templateUrl: './delivery-agent.component.html',
  styleUrls: ['./delivery-agent.component.css']
})
export class DeliveryAgentComponent implements OnInit {

  orders;

  constructor(private deliveryAgentService:DeliveryAgentService) { }

  ngOnInit() {
    this.deliveryAgentService.getOrdersByNotAssigned().subscribe((res)=>{
      console.log(res);
      this.orders = res;
    })
  }

  onClickAcceptOrder(event,order){
    event.target.textContent = "Accepted";
   this.deliveryAgentService.acceptOrder(order).subscribe((res)=>{
     console.log(res);
     this.orders = res;
   });
  }

}
