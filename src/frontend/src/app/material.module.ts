import { NgModule } from '@angular/core';

import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatBadgeModule } from '@angular/material/badge';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDialogModule } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatChipsModule } from '@angular/material/chips';
import { MatTableModule } from '@angular/material/table';


@NgModule({
  exports: [
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatMenuModule,
    MatProgressBarModule,
    MatCardModule,
    MatTabsModule,
    MatInputModule,
    MatFormFieldModule,
    MatSnackBarModule,
    MatBadgeModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
    MatDialogModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatChipsModule,
    MatTableModule,
  ]
})
export class MaterialModule { }
