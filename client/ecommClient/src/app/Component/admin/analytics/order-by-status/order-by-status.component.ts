import { Component,Input } from '@angular/core';
import { AngularMaterialModule } from '../../../../AngularMaterialModule';

@Component({
  selector: 'app-order-by-status',
  standalone: true,
  imports: [AngularMaterialModule],
  templateUrl: './order-by-status.component.html',
  styleUrl: './order-by-status.component.scss'
})
export class OrderByStatusComponent {
   
  @Input() data:any;
}
