import {NgModule} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatMenuModule } from '@angular/material/menu';
import { MatRadioModule } from '@angular/material/radio';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDividerModule } from '@angular/material/divider';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
@NgModule({
    exports: [
        MatButtonModule,
      MatCardModule,
      MatInputModule,
      MatToolbarModule,
      MatSelectModule,
      MatProgressSpinnerModule,
      MatSnackBarModule,
      MatFormFieldModule,
      MatIconModule,
      MatChipsModule,
      MatMenuModule,
      MatRadioModule,
      MatPaginatorModule,
      MatDividerModule,
      MatDatepickerModule,
      MatNativeDateModule,
      MatDialogModule,
      MatTableModule
    
    ]
  })
  export class AngularMaterialModule{ }
  