import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  @Input() brandName!: string;
  private year = new Date().getFullYear();

  project = 'angular-ecommerce';
  copyrightLine1 = `Powered by st.and©${this.year}.`;
  copyrightLine2 = 'Code licensed under an MIT License.';
}
