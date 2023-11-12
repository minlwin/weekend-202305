import { AfterContentChecked, AfterContentInit, AfterViewChecked, AfterViewInit, Directive, DoCheck, OnChanges, OnDestroy, OnInit, SimpleChanges } from "@angular/core";

@Directive()
export class BaseView implements OnInit, OnChanges, DoCheck, AfterContentInit, AfterContentChecked, AfterViewInit, AfterViewChecked, OnDestroy{

  constructor(private name:string) {}

  ngOnInit(): void {
    console.log(`OnInit : ${this.name}`)
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(`OnChanges : ${this.name}`)
  }

  ngDoCheck(): void {
    console.log(`DoCheck : ${this.name}`)
  }

  ngAfterContentInit(): void {
    console.log(`AfterContentInit : ${this.name}`)
  }

  ngAfterContentChecked(): void {
    console.log(`AfterContentChecked : ${this.name}`)
  }

  ngAfterViewInit(): void {
    console.log(`AfterViewInit : ${this.name}`)
  }

  ngAfterViewChecked(): void {
    console.log(`AfterViewChecked : ${this.name}`)
  }

  ngOnDestroy(): void {
    console.log(`OnDestroy : ${this.name}`)
  }
}
