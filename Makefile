NAME=appointments-management
VERSION=$(shell cat VERSION)
REPO=poojaak



build:
	docker build -t $(REPO)/$(NAME):$(VERSION) .

tag-latest:
	docker tag $(REPO)/$(NAME):$(VERSION) $(REPO)/$(NAME):latest

tag-develop: build
	docker tag $(REPO)/$(NAME):$(VERSION) $(REPO)/$(NAME):develop
	docker push $(REPO)/$(NAME):develop


run: build
	docker run \
	-p 8080:8080 \
	-t $(REPO)/$(NAME):$(VERSION)

install: build
	docker push $(REPO)/$(NAME):$(VERSION)

