import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject, catchError, filter, map, of, switchMap, take, takeLast, takeUntil, takeWhile, tap } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';
import { ProductCategoryService } from 'src/app/services/product-category.service';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/shared/models/product.model';
import { Status } from 'src/app/shared/models/status.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit, OnDestroy {
    /* Events */
    private destroy$ = new Subject<void>();
    /* States */
    public id = "";
    public status$ = new BehaviorSubject<Status>('initial');
    private idParamMap$ = this.route.paramMap
      .pipe(
        map((paramMap: ParamMap) => paramMap.get('id')),
        filter(Boolean),
        tap(id => this.id = id)
      );
    public product$: Observable<Product | null> = this.idParamMap$
      .pipe(  
        switchMap(productId =>  this.productService.product$(parseInt(productId))),
        catchError(err => of(null))
      );

    constructor(
      private route: ActivatedRoute,
      private router: Router,
      private productService: ProductService,
      private cartService: CartService,
      public productCategoryService: ProductCategoryService
    ) {}
  
    ngOnInit(): void {
      // [Set] status$ 'pending' [When] idParamMap$ emit
      this.idParamMap$
        .pipe(
          takeUntil(this.destroy$)
        )
        .subscribe(
          () => this.status$.next('pending')
        );
      // [Set] status$ 'succces' or 'error' [When] product$ emit
      this.product$
        .pipe(
          takeUntil(this.destroy$)
        )
        .subscribe(
          product => {
            if (product) {
              this.status$.next('success')
            } else {
              this.status$.next('error')
              this.router.navigate(['error','404']);
            }
          }
        )

    }
  
    ngOnDestroy(): void {
      this.destroy$.next();
      this.destroy$.complete();
    }
  
    public addProductToCart(id: number): void {
      this.cartService.addProduct(id);
    }

}
