import { Directive, ElementRef, Input, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appQuote]',
  standalone: true
})
export class QuoteDirective {

  @Input()
  set appQuote(value:string) {
    this.renderer.setStyle(this.eleRef.nativeElement, "background-color", value)
  }

  constructor(private eleRef:ElementRef, private renderer:Renderer2) {
    this.renderer.setStyle(eleRef.nativeElement, "padding", "20px")
  }

}
