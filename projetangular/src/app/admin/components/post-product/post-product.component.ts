import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from 'src/app/admin/services/admin.service'; // Assuming this path

@Component({
  selector: 'app-post-product', // Replace with your actual selector
  templateUrl: './post-product.component.html',
  styleUrls: ['./post-product.component.scss']
})
export class PostProductComponent implements OnInit {
  productForm!: FormGroup;
  listOfCategories: any[] = []; // Consider defining a more specific interface for categories
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      categoryId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });

    this.getAllCategories();
  }

  onFileSelected(event: any): void {
    if (event.target.files && event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
      this.previewImage();
    }
  }

  previewImage(): void {
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }

  getAllCategories(): void {
    this.adminService.getAllCategories().subscribe(res=>{
     
        this.listOfCategories = res;
      })
     
  }

  addProduct(): void {
    if (this.productForm.valid) {
      const formData = new FormData();
      formData.append('img', this.selectedFile as Blob); // Ensure selectedFile is treated as Blob
      formData.append('categoryId', this.productForm.get('categoryId')?.value);
      formData.append('name', this.productForm.get('name')?.value);
      formData.append('description', this.productForm.get('description')?.value);
      formData.append('price', this.productForm.get('price')?.value);

      this.adminService.addProduct(formData).subscribe({
        next: (res) => {
          if (res && res.id !== null) { // Assuming 'res' has an 'id' property on success
            this.snackBar.open('Product Posted Successfully!', 'Close', {
              duration: 5000
            });
            this.router.navigateByUrl('/admin/dashboard');
          } else {
            // Assuming 'res.message' contains the error message
            this.snackBar.open(res?.message || 'An unknown error occurred!', 'ERROR', {
              duration: 5000
            });
          }
        },
        error: (error) => {
          console.error('Error adding product:', error);
          this.snackBar.open('Failed to add product. Please try again.', 'ERROR', {
            duration: 5000
          });
        }
      });
    } else {
      for (const controlName in this.productForm.controls) {
        if (this.productForm.controls.hasOwnProperty(controlName)) {
          this.productForm.controls[controlName].markAsDirty();
          this.productForm.controls[controlName].updateValueAndValidity();
        }
      }
      this.snackBar.open('Please fill all required fields correctly.', 'ERROR', {
        duration: 5000
      });
    }
  }}