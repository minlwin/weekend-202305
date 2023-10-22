import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'images'
})
export class ImagesPipe implements PipeTransform {

  transform(value: string | undefined | null, ...args: unknown[]): unknown {

    if(value) {
      return `http://localhost:8080/images/${value}`
    }

    return '/assets/images/default.jpg';
  }

}
