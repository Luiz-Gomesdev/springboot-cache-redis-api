terraform {
  backend "remote" {
    organization = "luiz-gms-organization"

    workspaces {
      name = "luiz-gms-workspace"
    }
  }
}

resource "null_resource" "example" {
  triggers = {
    value = "A example resource that does nothing!"
  }
}